/*
 * wueasy - A Java Distributed Rapid Development Platform.
 * Copyright (C) 2017-2019 wueasy.com

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package webpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wueasy.admin.entity.TemplateCatalog;
import com.wueasy.admin.template.Context;
import com.wueasy.admin.template.FMWebpartParser;
import com.wueasy.admin.template.util.CatalogHelper;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public class HeaderWebpart extends FMWebpartParser{

	@Override
	public Map<String, Object> getModel(Context context, Map<String, String> webpartProp) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//获取一级栏目
		
		List<TemplateCatalog> catalogList = CatalogHelper.getChildrenCatalog(3L);
		
		map.put("catalogList", catalogList);
		
		return map;
	}

}
