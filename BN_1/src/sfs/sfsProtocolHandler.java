package sfs;

public interface sfsProtocolHandler {
    void handlePUT(String fileName);

    void handleGET(String fileName);

    void handleERROR(String filename);

    void handleOK(String fileName);


}
