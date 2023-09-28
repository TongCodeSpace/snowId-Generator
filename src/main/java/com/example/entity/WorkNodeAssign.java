package com.example.entity;

import com.example.dao.WorkNodeDao;
import com.example.entity.enums.WorkNodeEnum;
import com.example.utils.DockerUtils;
import com.example.utils.NetUtils;

import javax.annotation.Resource;

import java.util.Random;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class WorkNodeAssign {

    @Resource
    private WorkNodeDao workerNodeDAO;

    /**
     * 获取分配的 workId
     */
    public long assignWorkerId() {
        // build worker node entity
        WorkNode workerNodeEntity = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        workerNodeDAO.addWorkerNode(workerNodeEntity);
        LOGGER.info("Add worker node:" + workerNodeEntity);

        return workerNodeEntity.getId() % 1024;
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private WorkNode buildWorkerNode() {
        WorkNode workerNodeEntity = new WorkNode();
        if (DockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkNodeEnum.CONTAINER.value());
            workerNodeEntity.setHostname(DockerUtils.getDockerHost());
            workerNodeEntity.setPort(DockerUtils.getDockerPort());

        } else {
            workerNodeEntity.setType(WorkNodeEnum.ACTUAL.value());
            workerNodeEntity.setHostname(NetUtils.getLocalAddress());
            Random random = new Random();
            workerNodeEntity.setPort(System.currentTimeMillis() + "-" + random.nextInt(100000));
        }

        return workerNodeEntity;
    }
}
