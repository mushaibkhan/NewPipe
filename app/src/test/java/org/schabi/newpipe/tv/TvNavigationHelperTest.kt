/*
 * SPDX-FileCopyrightText: 2026 NewPipe e.V. <https://newpipe-ev.de>
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.tv

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.schabi.newpipe.extractor.stream.StreamInfoItem
import org.schabi.newpipe.extractor.stream.StreamType
import org.schabi.newpipe.player.playqueue.SinglePlayQueue
import org.schabi.newpipe.tv.ui.TvPlayerActivity
import org.schabi.newpipe.tv.util.TvNavigationHelper

class TvNavigationHelperTest {

    @Test
    fun testTvNavigationHelperConstants() {
        assertEquals("tv_service_id", TvNavigationHelper.KEY_SERVICE_ID)
        assertEquals("tv_url", TvNavigationHelper.KEY_URL)
        assertEquals("tv_title", TvNavigationHelper.KEY_TITLE)
        assertEquals("tv_search_query", TvNavigationHelper.KEY_SEARCH_QUERY)
    }

    @Test
    fun testTvPlayerActivityPendingQueue() {
        val streamItem = StreamInfoItem(
            0,
            "https://example.com/video",
            "Title",
            StreamType.VIDEO_STREAM
        )
        val queue = SinglePlayQueue(streamItem)

        TvPlayerActivity.setPendingPlayQueue(queue)
        assertNotNull(TvPlayerActivity.getPendingPlayQueue())
        // Clear pending queue
        TvPlayerActivity.setPendingPlayQueue(null)
    }
}
