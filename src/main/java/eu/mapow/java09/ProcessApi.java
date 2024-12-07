package eu.mapow.java09;

import java.lang.ProcessHandle.Info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author menas
 * @since 2024-12-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProcessApi {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessApi.class);

  public static Info printAll() {
    ProcessHandle.allProcesses().forEach(process -> {
      LOG.debug("----------------------------------------");
      LOG.debug("PID:              {}", process.pid());
      LOG.debug("Info:             {}", process.info());
      LOG.debug("arguments:        {}", process.info().arguments());
      LOG.debug("commandLine:      {}", process.info().commandLine());
      LOG.debug("startInstant:     {}", process.info().startInstant());
      LOG.debug("totalCpuDuration: {}", process.info().totalCpuDuration());
      LOG.debug("----------------------------------------");
    });
    LOG.debug("Number of processes: {}", ProcessHandle.allProcesses().count());
    return ProcessHandle.current().info();
  }
}
