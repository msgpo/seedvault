package com.stevesoltys.backup.service;

import android.app.backup.IBackupManager;
import android.os.RemoteException;
import android.os.ServiceManager;

import com.stevesoltys.backup.session.backup.BackupSession;
import com.stevesoltys.backup.session.backup.BackupSessionObserver;
import com.stevesoltys.backup.session.restore.RestoreSession;
import com.stevesoltys.backup.session.restore.RestoreSessionObserver;

import java.util.Set;

/**
 * @author Steve Soltys
 */
public class TransportService {

    private static final String BACKUP_TRANSPORT = "com.stevesoltys.backup.transport.ConfigurableBackupTransport";

    private final IBackupManager backupManager;

    public TransportService() {
        backupManager = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
    }

    public BackupSession backup(BackupSessionObserver observer, Set<String> packages) throws RemoteException {

        if (!BACKUP_TRANSPORT.equals(backupManager.getCurrentTransport())) {
            backupManager.selectBackupTransport(BACKUP_TRANSPORT);
        }

        if (!backupManager.isBackupEnabled()) {
            backupManager.setBackupEnabled(true);
        }

        BackupSession backupSession = new BackupSession(backupManager, observer, packages);
        backupSession.start();
        return backupSession;
    }

    public RestoreSession restore(RestoreSessionObserver observer, Set<String> packages) throws RemoteException {

        if (!BACKUP_TRANSPORT.equals(backupManager.getCurrentTransport())) {
            backupManager.selectBackupTransport(BACKUP_TRANSPORT);
        }

        if (!backupManager.isBackupEnabled()) {
            backupManager.setBackupEnabled(true);
        }

        RestoreSession restoreSession = new RestoreSession(backupManager, observer, packages);
        restoreSession.start();
        return restoreSession;
    }
}
