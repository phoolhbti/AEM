package com.citraining.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model (adaptables = Resource.class)
public class ColumnControl {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// inject dialoig values
	@Inject
	@Optional
	public String desktopColumns;

	// inject dialoig values
	@Inject
	@Optional
	public String tabletColumns;

	private List<Columns> col;

	@PostConstruct
	protected void init() {
		logger.info("Column Control  **** INIT ***");
		col = new ArrayList<>();

		if (desktopColumns != null || tabletColumns != null){
			int deskTopCols = Integer.parseInt(desktopColumns);
			int tabCols = Integer.parseInt(tabletColumns);
			logger.error("Column Control **** Deswktop cols ***{}", deskTopCols);
			logger.error("Column Control **Tablet Cols **** ***{}", tabCols);
			int mdVal = 12 / deskTopCols;
			int smVal = 12 / tabCols;
			logger.error("FooterGlobal **** smval ***{}", smVal);
			for (int i = 0; i < deskTopCols; i++){
				Columns item = new Columns();
				item.setCount(i);
				item.setDeskVal(mdVal);
				item.setTabVal(smVal);
				logger.error("Column Control **{}", item.toString());
				col.add(item);
			}
		}
		setCol(col);
	}

	public List<Columns> getCol() {
		return col;
	}

	public void setCol(List<Columns> col) {
		this.col = col;
	}

}