package de.seifi.videomanagerJsp.helper;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.seifi.videomanagerJsp.models.PathSubtitleModel;

public class FileData {

  public static String OutputExtentionTeil = "_out";

  public String Path;
  public String OutputPath;
  public String Base64Path;
  public String FolderPath;
  public String SubtitlePath;
  public String Base64SubtitlePath;
  public String SubtitleUrl;
  public String Name;
  public boolean hasSubtitle;
  public boolean isMedia;
  public boolean isInProcess;
  public boolean isInConvertProcess;
  public boolean isInSubtitleProcess;
  public boolean isConverted;
  public boolean hasConverted;

  public String MkvMergPath;

  @Autowired
  private final PathSubtitlesHandler pathSubtitlesHandler;

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

  public FileData(final String path, final PathSubtitlesHandler pathSubtitlesHandler) {

    this.pathSubtitlesHandler = pathSubtitlesHandler;

    this.MkvMergPath = this.getClass().getClassLoader().getResource("static/mkv/mkvmerge.exe").getPath();

    final File file = new File(path);

    this.Name = file.getName();
    this.FolderPath = file.getParent();
    this.Path = path;
    this.isMedia = isFileMedia(path);

    this.hasSubtitle = false;
    this.SubtitlePath = "";
    final String extension = getFileExtention(path);
    if (extension.length() > 0) {
      this.SubtitlePath = path.substring(0, path.length() - 4) + ".srt";
      this.hasSubtitle = (new File(this.SubtitlePath)).exists();

      this.OutputPath = path.substring(0, path.length() - 4) + OutputExtentionTeil + "." + extension;
    }

    this.isConverted = this.Name.indexOf(OutputExtentionTeil) > 0;
    this.hasConverted = new File(this.OutputPath).exists();

    byte[] bytesEncoded = Base64.getEncoder().encode(this.Path.getBytes());
    this.Base64Path = new String(bytesEncoded);

    bytesEncoded = Base64.getEncoder().encode(this.SubtitlePath.getBytes());
    this.Base64SubtitlePath = new String(bytesEncoded);

    this.SubtitleUrl = "https://subscene.com/subtitles/release?q=" + this.Name;

    final PathSubtitleModel subpath = pathSubtitlesHandler.getPathSubtitlesFromPath(this.FolderPath);

    if (subpath != null) {
      this.SubtitleUrl = subpath.getSuburl();
    }

    this.isInProcess = false;
    this.isInConvertProcess = false;
    this.isInSubtitleProcess = false;
  }

  public String getBase64OutputFilePath() {

    final byte[] bytesEncoded = Base64.getEncoder().encode(this.OutputPath.getBytes());
    return new String(bytesEncoded);

  }
}
