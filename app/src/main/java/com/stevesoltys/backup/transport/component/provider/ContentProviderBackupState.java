package com.stevesoltys.backup.transport.component.provider;

import android.os.ParcelFileDescriptor;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * @author Steve Soltys
 */
class ContentProviderBackupState {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private ParcelFileDescriptor inputFileDescriptor;

    private ParcelFileDescriptor outputFileDescriptor;

    private InputStream inputStream;

    private ZipOutputStream outputStream;

    private Cipher cipher;

    private long bytesTransferred;

    private String packageName;

    private byte[] salt;

    private SecretKey secretKey;

    ContentProviderBackupState() {
        salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
    }

    long getBytesTransferred() {
        return bytesTransferred;
    }

    void setBytesTransferred(long bytesTransferred) {
        this.bytesTransferred = bytesTransferred;
    }

    Cipher getCipher() {
        return cipher;
    }

    void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    ParcelFileDescriptor getInputFileDescriptor() {
        return inputFileDescriptor;
    }

    void setInputFileDescriptor(ParcelFileDescriptor inputFileDescriptor) {
        this.inputFileDescriptor = inputFileDescriptor;
    }

    InputStream getInputStream() {
        return inputStream;
    }

    void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    ParcelFileDescriptor getOutputFileDescriptor() {
        return outputFileDescriptor;
    }

    void setOutputFileDescriptor(ParcelFileDescriptor outputFileDescriptor) {
        this.outputFileDescriptor = outputFileDescriptor;
    }

    ZipOutputStream getOutputStream() {
        return outputStream;
    }

    void setOutputStream(ZipOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    String getPackageName() {
        return packageName;
    }

    void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    byte[] getSalt() {
        return salt;
    }

    SecretKey getSecretKey() {
        return secretKey;
    }

    void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
