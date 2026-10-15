/*
 * SPDX-FileCopyrightText: 2026 NewPipe e.V. <https://newpipe-ev.de>
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.tv.presenter;

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;

import org.schabi.newpipe.extractor.stream.StreamInfo;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;

/**
 * Leanback {@link AbstractDetailsDescriptionPresenter} binding stream metadata on Android TV.
 *
 * <p>Populates video title, uploader name, upload date, and full synopsis text in the
 * details overview pane of {@link org.schabi.newpipe.tv.ui.TvVideoDetailsFragment}.</p>
 *
 * @see androidx.leanback.widget.AbstractDetailsDescriptionPresenter
 */
public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {

    @Override
    protected void onBindDescription(final ViewHolder vh, final Object item) {
        if (item instanceof StreamInfo) {
            final StreamInfo info = (StreamInfo) item;
            vh.getTitle().setText(info.getName());

            final StringBuilder subtitle = new StringBuilder();
            if (info.getUploaderName() != null) {
                subtitle.append(info.getUploaderName());
            }
            if (info.getTextualUploadDate() != null) {
                if (subtitle.length() > 0) {
                    subtitle.append(" • ");
                }
                subtitle.append(info.getTextualUploadDate());
            }
            vh.getSubtitle().setText(subtitle.toString());

            if (info.getDescription() != null && info.getDescription().content() != null) {
                vh.getBody().setText(info.getDescription().content());
            }
        } else if (item instanceof StreamInfoItem) {
            final StreamInfoItem infoItem = (StreamInfoItem) item;
            vh.getTitle().setText(infoItem.getName());
            vh.getSubtitle().setText(infoItem.getUploaderName());
        }
    }
}
