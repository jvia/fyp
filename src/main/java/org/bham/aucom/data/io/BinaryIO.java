package org.bham.aucom.data.io;

import nu.xom.ParsingException;

import java.io.*;

public abstract class BinaryIO<T> implements IOInterface<T> {
    Class<T> cl;

    public BinaryIO(Class<T> cl) {
        this.cl = cl;
    }

    @Override
    public T read(File fileToLoadFrom) throws ParsingException, IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileToLoadFrom));
        Object faultDetectionModel;
        try {
            faultDetectionModel = objectInputStream.readObject();
            objectInputStream.close();
            return this.cl.cast(faultDetectionModel);
        } catch (ClassNotFoundException exception) {
            throw new ParsingException("tried to parse an unknown class from " + fileToLoadFrom.getAbsolutePath());
        }
    }

    @Override
    public boolean write(T faultDetectionModelTowrite, File file) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(faultDetectionModelTowrite);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

}