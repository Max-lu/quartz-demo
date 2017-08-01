package cn.maxlu.demo.quartz.cron;

import cn.maxlu.demo.quartz.QuartzHelper;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by luwei on 2017/8/1.
 */

public class QuartzTest {
    public static void main(String[] args) {
        Scheduler scheduler = QuartzHelper.getScheduler();
        addJob(scheduler);
    }

    private static void addJob(Scheduler scheduler) {
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job2", "group2")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger2", "group2")
                .startNow()
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
