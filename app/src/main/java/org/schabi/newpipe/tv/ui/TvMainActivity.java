/*
 * SPDX-FileCopyrightText: 2026 NewPipe e.V. <https://newpipe-ev.de>
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.tv.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import org.schabi.newpipe.R;

/**
 * Main entry activity for the Android TV 10-foot experience.
 *
 * <p>Declared with {@code android.intent.category.LEANBACK_LAUNCHER} in the AndroidManifest,
 * this activity is launched automatically on Android TV and Google TV devices. It hosts
 * {@link TvBrowseFragment} inside {@code activity_tv_main} to present categorized content rows
 * (Trending, Subscriptions, History, and Playlists) tailored for D-pad remote navigation.</p>
 *
 * @see <a href="https://developer.android.com/design/ui/tv">Android TV Design Guidelines</a>
 * @see androidx.leanback.app.BrowseSupportFragment
 */
public class TvMainActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.tv_main_container, new TvBrowseFragment())
                    .commit();
        }
    }
}
