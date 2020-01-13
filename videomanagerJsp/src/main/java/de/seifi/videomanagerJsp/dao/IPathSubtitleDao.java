package de.seifi.videomanagerJsp.dao;

import java.util.List;

import de.seifi.videomanagerJsp.models.PathSubtitleModel;

public interface IPathSubtitleDao {

  PathSubtitleModel readById(Long id);

  PathSubtitleModel save(PathSubtitleModel folder);

  void delete(PathSubtitleModel folder);

  List<PathSubtitleModel> readAll();

  PathSubtitleModel getPathSubtitlesFromPath(String path);

  PathSubtitleModel getPathSubtitlesFromSubUrl(String suburl);
}
