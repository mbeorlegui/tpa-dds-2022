package domain.scheduler;

import domain.organizacion.Organizaciones;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class JobScheduler {

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
    private final String LINK_TO_SEND = "https://www.google.com/";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
      Organizaciones.getInstance().enviarGuiaDeRecomendaciones(LINK_TO_SEND);
    }
  }
}