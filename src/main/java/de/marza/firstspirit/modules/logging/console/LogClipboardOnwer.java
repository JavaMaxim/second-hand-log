package de.marza.firstspirit.modules.logging.console;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;


public class LogClipboardOnwer implements ClipboardOwner {

    private static LogClipboardOnwer self = new LogClipboardOnwer();

    public static ClipboardOwner getInstance() {
        return self;
    }

    @Override
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) {
        //who cares?
    }
}