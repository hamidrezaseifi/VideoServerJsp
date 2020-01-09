package de.seifi.videomanagerJsp.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.seifi.videomanagerJsp.bl.FoldersHandler;
import de.seifi.videomanagerJsp.bl.PathSubtitlesHandler;

public class FileHelper {

  public static List<FileData> getAllMediaFiles(final String path, final FoldersHandler foldersHandler,
      final PathSubtitlesHandler pathSubtitlesHandler) {

    final List<FileData> files = new ArrayList<>();

    final File folder = new File(path);
    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        final FileData filedata = new FileData(listOfFiles[i].getAbsolutePath(), foldersHandler, pathSubtitlesHandler);
        if (filedata.isMedia) {
          files.add(filedata);
        }

      }
      else if (listOfFiles[i].isDirectory()) {
        files.addAll(getAllMediaFiles(listOfFiles[i].getAbsolutePath(), foldersHandler, pathSubtitlesHandler));
      }
    }

    return files;
  }

  public static List<SubtitleData> getAllSubtitleFiles(final String path) {

    final List<SubtitleData> files = new ArrayList<>();

    final File folder = new File(path);
    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        final SubtitleData filedata = new SubtitleData(listOfFiles[i].getAbsolutePath());
        if (filedata.isSubtitle) {
          files.add(filedata);
        }

      }
      else if (listOfFiles[i].isDirectory()) {
        files.addAll(getAllSubtitleFiles(listOfFiles[i].getAbsolutePath()));
      }
    }

    return files;
  }
}
