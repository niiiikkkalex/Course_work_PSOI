package org.example;
import acceptionData.AcceptDataAdmin;
import acceptionData.AcceptDataClient;
import dao.ClientDao;
import dao.LoginKeysDao;
import entity.Client;
import entity.LoginKeys;
import lombok.SneakyThrows;
import service.ClientService;
import service.KeysService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class RunnableServer implements Runnable {
    private static Socket clientSocket;
    public RunnableServer(Socket client) {
        RunnableServer.clientSocket = client;
    }

    private Client client;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Connection enable!");
        int flagInput = 0;
        client = new Client();
        ObjectInputStream sois = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream soos = new ObjectOutputStream(clientSocket.getOutputStream());
        try {
            while(true) {
                flagInput = (int)sois.readObject();
                switch (flagInput)
                {
                    case 1://авторизация
                    {
                        String log = (String) sois.readObject();

                        String pass = (String) sois.readObject();
                        LoginKeys auth = new LoginKeys();
                        auth.setLogin(log);
                        auth.setPassword(pass);
                        //soos.writeObject(1);
                        int сheck = 0;
                        сheck = KeysService.checkUser(auth);
                        soos.writeObject(сheck);
                        if (сheck == 2) {
                            auth = KeysService.getLoginKeys(auth);
                            client = ClientDao.findClientByIdKeys(auth.getId_keys());
                        }
                        break;
                    }
                    case 2: {//регистрация
                        String fname = (String) sois.readObject();
                        String lname = (String) sois.readObject();
                        String number = (String) sois.readObject();
                        String login = (String) sois.readObject();
                        String password = (String) sois.readObject();

                        LoginKeys regKeys = new LoginKeys();
                        regKeys.setLogin(login);
                        regKeys.setPassword(password);


                        Client registration = new Client();
                        registration.setFirst_name(fname);
                        registration.setLast_name(lname);
                        registration.setPhone_number(number);

                        registration.setId_keys(regKeys);
                        int num = 0;
                        num = KeysService.checkClient(regKeys);
                        if(num == 1){
                            ClientDao.save(registration);
                        }
                        soos.writeObject(num);
                        break;
                    }
                    case 3:{//работа с админом
                        AcceptDataAdmin menu = new AcceptDataAdmin();
                        menu.MenuAdmin((int)sois.readObject(), sois, soos);
                        break;
                    }
                    case 4:{//работа с посетителем
                        AcceptDataClient menu = new AcceptDataClient();
                        menu.MenuClient((int)sois.readObject(), sois, soos, (int)client.getId_client(), client);
                        break;
                    }
                }
            }
            /*try {
                sois.close();
                soos.close();
                clientSocket.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


