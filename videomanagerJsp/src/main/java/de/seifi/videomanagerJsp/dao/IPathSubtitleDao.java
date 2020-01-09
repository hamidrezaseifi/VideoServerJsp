package de.seifi.videomanagerJsp.dao;

import java.util.List;

import de.seifi.videomanagerJsp.models.PathSubtitleModel;

public interface IPathSubtitleDao {

  List<PathSubtitleModel> readAll();
}
