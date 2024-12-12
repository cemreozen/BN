package sfs;

import java.io.IOException;

public interface sfsProtocolHandler {
    void handlePUT(String fileName) throws IOException;

    void handleGET(String fileName) throws IOException;

    void handleERROR(String filename) throws IOException;

    void handleOK(String fileName) throws IOException;
}
