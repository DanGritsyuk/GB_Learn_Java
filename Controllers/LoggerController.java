package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoggerController {
    private static final String LOG_FOLDER = "logs";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private String _folder;

    private File logFile;
    private FileWriter fileWriter;

    public LoggerController() throws IOException {
        this._folder = LOG_FOLDER;
        CreateLogFile();
    }

    public LoggerController(String folder) throws IOException {
        this._folder = LOG_FOLDER + "\\" + folder;
        CreateLogFile();
    }

    private void CreateLogFile() throws IOException {
        // Создаем папку для логов, если её нет
        File logFolder = new File(LOG_FOLDER);
        if (!logFolder.exists()) {
            logFolder.mkdir();
        }

        // Создаем файл лога с названием вида "logs/2021-12-31.log"
        String fileName = LOG_FOLDER + "/" + DATE_FORMAT.format(Calendar.getInstance().getTime()) + ".log";
        logFile = new File(fileName);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        // Открываем поток для записи в файл
        fileWriter = new FileWriter(logFile, true);
    }

    public void log(String message) throws IOException {
        // Если текущий файл лога не соответствует текущей дате, то создаем новый файл
        String currentDate = DATE_FORMAT.format(Calendar.getInstance().getTime());
        if (!currentDate.equals(DATE_FORMAT.format(logFile.lastModified()))) {
            fileWriter.close();
            CreateLogFile();
        }

        // Записываем сообщение в лог с указанием времени
        String logMessage = "[" + TIME_FORMAT.format(Calendar.getInstance().getTime()) + "] " + message + "\n";
        fileWriter.write(logMessage);
        fileWriter.flush();
    }
}