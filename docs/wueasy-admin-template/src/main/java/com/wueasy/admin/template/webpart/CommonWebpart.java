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
package com.wueasy.admin.template.webpart;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wueasy.admin.template.Context;
import com.wueasy.admin.template.WebpartParser;
import com.wueasy.base.util.StringHelper;

/**
 * 公共模板
 * @author: fallsea
 * @version 1.0
 */
public class CommonWebpart implements WebpartParser{
	
	private static Logger logger = LoggerFactory.getLogger(CommonWebpart.class);

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
        String provider = webpartProp.get("provider");

        //若页面中视图为空，则根据provider实现类，寻找相应的缺省视图，缺省视图为(类名.view)
        if (StringHelper.isEmpty(viewStr))
        {
            viewStr = getDefaultView(provider);
        }

        WebpartParser parser = null;
        try {
			parser = (WebpartParser) Class.forName(provider).newInstance();
		} catch (InstantiationException e) {
			logger.error("",e);
		} catch (IllegalAccessException e) {
			logger.error("",e);
		} catch (ClassNotFoundException e) {
			logger.error("",e);
		}
        if(null==parser){
        	return "实现类加载异常:"+provider;
        }
        return parser.parse(context, webpartProp, viewStr);
    }

    /**
     * 获取默认view
     * @author: fallsea
     * @param provider
     * @return
     */
    private String getDefaultView(String provider)
    {
        StringBuffer buffer = new StringBuffer();
        InputStream inStream = null;
        try
        {
            String path = "/" + provider.replace(".","/");
            path = path + ".view";
            inStream = CommonWebpart.class.getResourceAsStream(path);
            if (inStream != null)
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                String lineStr = "";
                while ((lineStr = reader.readLine()) != null)
                {
                    buffer.append(lineStr + "\r\n");
                }
            }
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        finally
        {
            if (inStream != null)
            {
                try
                {
                    inStream.close();
                }
                catch (Exception ex)
                {
                }
            }
        }

        return buffer.toString();
    }

}
