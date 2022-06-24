package com.yu.events.rmi.rmibase;

import com.yu.events.EventContextSingle;
import com.yu.events.ExecEvent;
import com.yu.events.ExecEventType;
import com.yu.events.eventbase.AsyncDispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * rmi的实现
 *
 * @author YU
 * @date 2022-05-28 17:04
 */
public class RmiImplement extends UnicastRemoteObject implements RmiInterface {
    private AsyncDispatcher asyncDispatcher;

    public RmiImplement() throws RemoteException {
        super();
        this.asyncDispatcher = EventContextSingle.getSingleEventContext().getAsyncDispatcher();
    }

    @Override
    public void pushHiEvent(String message) {
        ExecEvent execEvent = new ExecEvent(ExecEventType.HI, asyncDispatcher, message);
        this.asyncDispatcher.dispatchEvent(execEvent);
    }
}
