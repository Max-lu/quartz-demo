package cn.maxlu.demo.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by luwei on 2017/8/1.
 */
public class QuartzHelper {

    private static Scheduler scheduler;

    private static void init() {
        if (scheduler == null) {
            synchronized (QuartzHelper.class) {
                if (scheduler == null) {
                    try {
                        scheduler = StdSchedulerFactory.getDefaultScheduler();
                    } catch (SchedulerException e) {
                        throw new RuntimeException("初始化quartz失败");
                    }
                    Runtime.getRuntime().addShutdownHook(new Thread(QuartzHelper::shutdown));
                }
            }
        }

    }

    private static void start() {
        try {
            init();
            if (scheduler.isStarted()) {
                return;
            }
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException("启动quartz失败");
        }
    }

    private static void shutdown() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException("停止quartz失败");
        }
    }

    public static Scheduler getScheduler() {
        start();
        return scheduler;
    }

}
