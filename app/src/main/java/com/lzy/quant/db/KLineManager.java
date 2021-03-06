/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzy.quant.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.lzy.quant.bean.KLine;

import java.util.List;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class KLineManager extends BaseDao<KLine> {

    private static final KLineManager instance = new KLineManager();

    public static KLineManager getInstance() {
        return instance;
    }

    private KLineManager() {
        super(DBHelper.getInstance());
    }

    @Override
    public KLine parseCursorToBean(Cursor cursor) {
        return KLine.parseCursorToBean(cursor);
    }

    @Override
    public ContentValues getContentValues(KLine kLine) {
        return KLine.getContentValues(kLine);
    }

    @Override
    public String getTableName() {
        return DBHelper.TABLE_KLINE;
    }

    @Override
    public void unInit() {
    }

    public List<KLine> query(String symbol, String period, int limit) {
        return query(null, "symbol = ? and period = ?", new String[]{symbol, period}, null, null, "id DESC", String.valueOf(limit));
    }

    public KLine query(long id, String symbol, String period) {
        return queryOne("id = ? and symbol = ? and period = ?", new String[]{id + "", symbol, period});
    }

    /**
     * 清空缓存
     */
    public boolean clear() {
        return deleteAll();
    }
}
