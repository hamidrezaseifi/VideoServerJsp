package de.seifi.videomanagerJsp.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SubtitleZipfileDownloader {

  public static boolean DownloadSrtZip(final String subtitlefilename, final String urlString) {

    String zipfile = subtitlefilename;
    zipfile = zipfile.toLowerCase().replace(".srt", ".zip");
    if (!saveUrl(zipfile, urlString)) {
      return false;
    }

    return unZipIt(zipfile, subtitlefilename);
  }

  public static boolean saveUrl(final String filename, final String urlString) {

    InputStream in = null;
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream(filename);

      final HttpURLConnection httpcon = (HttpURLConnection) new URL(urlString).openConnection();
      httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

      in = httpcon.getInputStream();

      // in = new BufferedInputStream(new URL(urlString).openStream());

      final byte data[] = new byte[1024];
      int count;
      while ((count = in.read(data, 0, 1024)) != -1) {
        fout.write(data, 0, count);
      }

      if (in != null) {
        in.close();
      }
      if (fout != null) {
        fout.close();
      }

      return true;

    }
    catch (final IOException ex) {
      System.out.println("Error : " + ex.getMessage());
    }
    finally {

    }

    return false;
  }

  public static boolean unZipIt(final String zipFile, final String outputFile) {

    final byte[] buffer = new byte[1024];

    try {

      // create output directory is not exists

      final FileInputStream fs = new FileInputStream(zipFile);
      final ZipInputStream zis = new ZipInputStream(fs);

      ZipEntry ze = zis.getNextEntry();

      while (ze != null) {

        final String fileName = ze.getName();
        if (fileName.toLowerCase().endsWith(".srt")) {
          final File newFile = new File(outputFile);

          // create all non exists folders
          // else you will hit FileNotFoundException for compressed folder
          new File(newFile.getParent()).mkdirs();

          final FileOutputStream fos = new FileOutputStream(newFile);

          int len;
          while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
          }

          fos.close();
          ze = zis.getNextEntry();
          break;
        }

      }

      zis.closeEntry();
      zis.close();

      final File f = new File(zipFile);
      f.delete();
      return true;

    }
    catch (final IOException ex) {
      ex.printStackTrace();
    }

    return false;
  }
}
