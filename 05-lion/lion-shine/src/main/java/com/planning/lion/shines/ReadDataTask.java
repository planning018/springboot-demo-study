package com.planning.lion.shines;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务 读取本地文件
 * @author planning
 * @since 2020-03-16 20:14
 **/
@Slf4j
public class ReadDataTask {

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(1,
                    new ThreadFactoryBuilder().setNameFormat("planning-read-thread-%d").setDaemon(false).build());

    private static final Gson GSON = new Gson();

    private String filePath;
    private volatile Map<String,Object> lastData;

    public ReadDataTask(String filePath){
        this.filePath = filePath;
    }

    public void init(){
        SCHEDULED_EXECUTOR.scheduleAtFixedRate(new ReadFileTask(), 60, 60, TimeUnit.SECONDS);
        lastData = fromFile(filePath);
    }

    /**
     * 测试使用，真实可以作为 本地缓存 使用
     * @return cache
     */
    public String getFileContent(){
        return GSON.toJson(lastData);
    }

    private Map<String, Object> fromFile(String filePath) {
        // use try-with-resource
        try (
                InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
                StringWriter writer = new StringWriter()
        ) {
            char[] buffer = new char[512];
            int count;
            while (-1 != (count = reader.read(buffer))) {
                writer.write(buffer, 0, count);
            }
            return GSON.fromJson(writer.toString(), new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            throw new RuntimeException("read file error");
        }
    }

    private class ReadFileTask implements Runnable {
        @Override
        public void run() {
            try {
                lastData = fromFile(filePath);
            } catch (Exception e) {
                log.warn("fail update cache from file : {}, error : {}", filePath, e.getMessage());
            }
        }

    }

}