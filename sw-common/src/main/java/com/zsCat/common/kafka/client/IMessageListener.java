package com.zsCat.common.kafka.client;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-24
 * @param <T> tag
 */
public interface IMessageListener <T extends java.io.Serializable>  {

    public void recvMessage(byte[] msg);
}
