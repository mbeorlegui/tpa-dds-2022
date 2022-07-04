import domain.organizacion.Organizacion;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class QuartzTest {

  public static void main(String[] args) {

    try {
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

      scheduler.start();

      // scheduler.shutdown();

      JobDetail job = newJob(OrganizacionJob.class)
          .withIdentity("send-notification")
          .build();

      // Tarea croneada para las 17hs de lunes a viernes
      // cronSchedule("seg min hs ? * dia")
      CronTrigger trigger = newTrigger().withIdentity("trigger-notification")
          .withSchedule(cronSchedule("0 0 17 ? * MON-FRI"))
          .forJob(job)
          .build();

      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException se) {
      se.printStackTrace();
    }


  }

  public static class OrganizacionJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
      Organizacion.enviarNotificacion();
    }
  }
}