package cpm.module.service;

import java.util.ArrayList;
import java.util.List;

import cpm.module.fileUtils.CastleFileUtil;
import cpm.module.model.book.Castle;
import cpm.module.model.book.DiscountType;

public class CastlesService {

	private List<Castle> castleList = new ArrayList<>();

	public List<Castle> getCastlesList() {
		if (castleList.isEmpty()) {
			castleList = CastleFileUtil.loadFile();
		}
		return castleList;
	}

	public DiscountType hasDiscount(String id) {

		return DiscountType.NONE;
	}

}
