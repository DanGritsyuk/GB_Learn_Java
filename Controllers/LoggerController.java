package Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

public class LoggerController {

    private String _logDirectory;
    private String _extension;
    private Logger _logger;

    public LoggerController(String loggerName, Boolean isXMLFormat) {
        try {
            this._logDirectory = "Logs\\" + loggerName;
            _extension = isXMLFormat ? ".xml" : ".log";
            _logger = Logger.getLogger(loggerName);
            _logger.setLevel(Level.INFO);
            createLogFile();

            FileHandler fileHandler = new FileHandler(getLogFilePath(), true);
            if (isXMLFormat) {
                fileHandler.setFormatter(new XMLFormatter());
            } else {
                fileHandler.setFormatter(new SimpleFormatter());
            }
            _logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        _logger.info(message);
    }

    public void Log(String level, String message) {
        _logger.log(Level.parse(level), message);
    }

    public void dispose() {
        var fileHandler = _logger.getHandlers()[0];
        fileHandler.close();
        _logger.removeHandler(fileHandler);
        _logger = null;
    }

    private void createLogFile() throws IOException {
        Path logPath = Paths.get(_logDirectory);
        if (!Files.exists(logPath)) {
            Files.createDirectories(logPath);
        }
        String logFilePath = getLogFilePath();
        Path path = Paths.get(logFilePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private String getLogFilePath() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        return _logDirectory + "\\" + currentDate + _extension;
    }
}