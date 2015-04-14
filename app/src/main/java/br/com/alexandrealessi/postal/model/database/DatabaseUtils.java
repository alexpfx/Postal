package br.com.alexandrealessi.postal.model.database;

import android.content.Context;
import br.com.alexandrealessi.postal.exceptions.PostalException;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexandre on 13/04/15.
 */
public class DatabaseUtils {


    public static final void copyToExternalStorage(String dbName, Context context) throws PostalException {
        File sdPath = context.getExternalCacheDir();
        File databasePath = context.getDatabasePath(dbName);
        if (!databasePath.exists()) {
            throw new PostalException("não pode escrever no sdcard: ");
        }
        if (!sdPath.canWrite()) {
            throw new PostalException("não pode escrever no sdcard: ");
        }
        File backup = new File(sdPath, dbName + ".bkp." + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
        copyFile(databasePath, backup);
    }

    public static final void copyFromAsset(String sourceDatabaseName, String destPath, Context context) throws PostalException {
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(sourceDatabaseName);
        } catch (IOException e) {
            throw new PostalException("não encontrado arquivo de banco de dados: " + sourceDatabaseName);
        }

        String outputFileName = destPath + "/" + sourceDatabaseName;
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(outputFileName);
        } catch (FileNotFoundException e) {
            throw new PostalException("arquivo nao encontrado: " + outputFileName);
        }
        try {
            copyStream(inputStream, outputStream);

        } catch (IOException e) {
            throw new PostalException("erro ao copiar stream: " + e.getMessage());
        } finally {
            closeStreams(inputStream, outputStream);
        }
    }

    private static void closeStreams(InputStream inputStream, OutputStream outputStream) {
        try {
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            throw new PostalException(e);
        }
    }

    private static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
    }


    private static void copyFile(File source, File dest) {

        FileChannel srcChannel = null;
        FileChannel dstChannel = null;
        try {
            srcChannel = new FileInputStream(source).getChannel();
            dstChannel = new FileOutputStream(dest).getChannel();
            dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
        } catch (IOException e) {
            throw new PostalException(e);
        } finally {
            closeChannels(srcChannel, dstChannel);
        }
    }

    private static void closeChannels(FileChannel srcChannel, FileChannel dstChannel) {
        try {
            srcChannel.close();
            dstChannel.close();
        } catch (IOException e) {
            throw new PostalException(e);
        }
    }


}
