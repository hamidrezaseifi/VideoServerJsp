package de.seifi.videomanagerJsp.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.seifi.videomanagerJsp.dao.IFolderDao;
import de.seifi.videomanagerJsp.dao.IPathSubtitleDao;
import de.seifi.videomanagerJsp.models.FolderModel;
import de.seifi.videomanagerJsp.models.PathSubtitleModel;

@Component
public class FileHelper {

  @Autowired
  private IPathSubtitleDao pathSubtitleDao;

  @Autowired
  private IFolderDao folderDao;

  public List<FileData> getAllMediaFiles(final FolderModel folderModel) {

    final List<FileData> files = new ArrayList<>();

    final File folder = new File(folderModel.getPath());
    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        final FileData filedata = new FileData(listOfFiles[i].getAbsolutePath());
        if (filedata.isMedia()) {
          files.add(filedata);
        }

      }
      else if (listOfFiles[i].isDirectory()) {
        files.addAll(this.getAllMediaFiles(listOfFiles[i].getAbsolutePath()));
      }
    }

    for (final FileData fileData : files) {
      final PathSubtitleModel subpath = this.pathSubtitleDao.getPathSubtitlesFromPath(fileData.getFolderPath());

      if (subpath != null) {
        fileData.setSubtitleUrl(subpath.getSuburl());
      }
    }

    return files;
  }

  private List<FileData> getAllMediaFiles(final String path) {

    final List<FileData> files = new ArrayList<>();

    final File folder = new File(path);
    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        final FileData filedata = new FileData(listOfFiles[i].getAbsolutePath());
        if (filedata.isMedia()) {
          files.add(filedata);
        }

      }
      else if (listOfFiles[i].isDirectory()) {
        files.addAll(this.getAllMediaFiles(listOfFiles[i].getAbsolutePath()));
      }
    }

    return files;
  }

  public List<SubtitleData> getAllSubtitleFiles(final String path) {

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
        files.addAll(this.getAllSubtitleFiles(listOfFiles[i].getAbsolutePath()));
      }
    }

    return files;
  }
}
