package de.seifi.videomanagerJsp.dao;

import java.util.List;

import de.seifi.videomanagerJsp.models.FolderModel;

public interface IFolderDao {

  FolderModel readById(Long id);

  FolderModel save(FolderModel folder);

  void delete(FolderModel folder);

  List<FolderModel> readAll();

}
