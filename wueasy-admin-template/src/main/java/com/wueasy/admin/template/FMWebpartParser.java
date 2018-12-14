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
package com.wueasy.admin.template;

import java.util.Map;

/**
 * 
 * @author: fallsea
 * @version 1.0
 */
public abstract class FMWebpartParser implements WebpartParser{

	/**
	 * 对部件进行解析，得到对应部件的HTML
	 * @author: fallsea
	 * @param context 解析上下文参数
	 * @param webpartProp 部件的属性
	 * @param viewStr 模板中的部件视图字串
	 * @return
	 */
    public String parse(Context context, Map<String,String> webpartProp, String viewStr)
    {
        //获得处理后的模型数据
        Map<String,Object> model = getModel(context, webpartProp);
        model.put("prop", webpartProp);
        return doParse(model, viewStr);
    }

    public abstract Map<String,Object> getModel(Context context, Map<String,String> webpartProp);

    /**
     * 解析器解析视图
     * @author: fallsea
     * @param model 模型数据
     * @param viewStr 视图字串
     * @return
     */
    public String doParse(Map<String,Object> model, String viewStr)
    {
        return FMTemplateGenerator.parseTemplate(model, viewStr);
    }
}
