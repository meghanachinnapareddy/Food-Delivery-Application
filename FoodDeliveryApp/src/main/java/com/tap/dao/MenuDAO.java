package com.tap.dao;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDAO {
	int addMenu(Menu m);
	List<Menu> getMenu(int id);
	Menu getMenuById(int id);
	void updateMenu(Menu r);
	void deleteMenu(int id);
	List<Menu>getAllMenu();

}
