package com.planning.lion.shines;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;

/**
 * 基于 ZK 的配置数据源
 * @author planning
 * @since 2020-03-17 14:34
 **/
@Slf4j
public class ZooKeeperConfigDataSource {

    private String zkServerUrl;
    private CuratorFramework curatorFramework;

    public ZooKeeperConfigDataSource(String zkServerUrl){
        this.zkServerUrl = zkServerUrl;
    }

    public void init(){
        log.info(">>>>>>>> zookeeper url is {}", zkServerUrl);

        curatorFramework = CuratorFrameworkFactory.newClient(zkServerUrl, 60 * 1000,
                                30 * 1000, new RetryNTimes(Integer.MAX_VALUE, 1000));
        curatorFramework.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.info(">>>>>>>>> zookeeper state changed to {}", newState);
            }
            // todo 未完待续，还未熟悉 Lion 源码，不太清楚如何抽离出一个小 demo

        });

    }
}