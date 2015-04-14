package br.com.alexandrealessi.postal.model.database;

import android.content.Context;
import android.os.Environment;
import com.google.common.io.Files;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexandre on 13/04/15.
 */
public class DatabaseUtils {


    public static final void copyToExternalStorage(String dbName, Context context) {
        File sdPath = context.getExternalCacheDir();

        File databasePath = context.getDatabasePath(dbName);
        if (!databasePath.exists()) {
            throw new RuntimeException("database não existe: " + dbName);
        }
        if (!sdPath.canWrite()) {
            throw new RuntimeException("não pode escrever no sdcard: ");
        }
        File backup = new File(sdPath, dbName + ".bkp." + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));

        try {
            copyFile(databasePath, backup);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.getMessage());
        }
    }

    public static final void copyFromAsset(String sourceDatabaseName, String destPath, Context context) {
        try {
            final InputStream inputStream = context.getAssets().open(sourceDatabaseName);
            String outputFileName = destPath +"/"+ sourceDatabaseName;
            OutputStream outputStream = new FileOutputStream(outputFileName);
            copyStream(inputStream, outputStream);
            closeStreams(inputStream, outputStream);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void closeStreams(InputStream inputStream, OutputStream outputStream) throws IOException {
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
    }


    private static void copyFile(File source, File dest) throws IOException {
        FileChannel srcChannel = new FileInputStream(source).getChannel();
        FileChannel dstChannel = new FileOutputStream(dest).getChannel();
        dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
        srcChannel.close();
        dstChannel.close();
    }


}
