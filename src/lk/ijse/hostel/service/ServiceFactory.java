package lk.ijse.hostel.service;

import lk.ijse.hostel.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostel.service.custom.impl.StudentServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }

    public static ServiceFactory getServiceFactory(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public static ServiceFactory getInstance(){
        return serviceFactory == null?(serviceFactory = new ServiceFactory()) : serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes types) {
        switch (types) {
            case ROOM:return (T)new RoomServiceImpl();
            case STUDENT:return (T)new StudentServiceImpl();
            case RESERVATION:return (T) new RoomServiceImpl();
            case KEYMONEY:return (T) new RoomServiceImpl();
            default:
                return null;
        }
    }
}
