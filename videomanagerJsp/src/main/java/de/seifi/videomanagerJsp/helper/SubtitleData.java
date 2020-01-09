package de.seifi.videomanagerJsp.helper;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class SubtitleData {

  public String Path;
  public String Base64Path;
  public String FolderPath;
  public String Name;
  public boolean isSubtitle;

  public static boolean isFileMedia(final String path) {

    final List<String> mediaExtentions = Arrays.asList(new String[] { "mpg", "mp4", "mkv", "avi" });
    final String extension = getFileExtention(path);

    return mediaExtentions.contains(extension);
  }

  public static boolean isFileSubtitle(final String path) {

    return getFileExtention(path).toLowerCase().equals("srt");
  }

  public static String getFileExtention(final String path) {

    String extension = "";

    final int i = path.lastIndexOf('.');
    if (i > 0) {
      extension = path.substring(i + 1);

    }

    return extension;
  }

  public SubtitleData(final String path) {

    final File file = new File(path);

    this.Name = file.getName();
    this.FolderPath = file.getParent();
    this.Path = path;

    final String extension = getFileExtention(path);
    if (extension.length() > 0) {
      this.isSubtitle = extension.toLowerCase().equals("srt");
    }

    final byte[] bytesEncoded = Base64.getEncoder().encode(this.Path.getBytes());
    this.Base64Path = new String(bytesEncoded);

  }

}
