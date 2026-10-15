package org.schabi.newpipe.cast.driver

import org.schabi.newpipe.cast.model.CastDevice
import org.schabi.newpipe.cast.session.CastSession
import org.schabi.newpipe.cast.session.CastStateListener

/**
 * Core driver abstraction enabling pluggable multi-backend media casting in NewPipe.
 *
 * <p>Designed to be completely protocol-agnostic and decoupled from NewPipe UI layers,
 * allowing diverse casting technologies to coexist under a unified interface:</p>
 * <ul>
 *   <li><b>Google Cast (V2/V3)</b>: Implemented by {@code ChromecastDriver} via raw TLS and protobuf.</li>
 *   <li><b>YouTube Lounge (MDX)</b>: Implemented by {@code YouTubeLoungeDriver} via DIAL and HTTP bind.</li>
 *   <li><b>Future Backends</b>: Designed for straightforward extension by community contributors
 *       to support UPnP/DLNA (AVTransport), FCast, and Matter Casting without touching the
 *       playback or UI layers.</li>
 * </ul>
 *
 * @see org.schabi.newpipe.cast.session.CastSession
 * @see org.schabi.newpipe.cast.session.CastSessionManager
 */
interface CastDriver {
    /**
     * Initiates asynchronous network discovery for compatible sink devices.
     *
     * @param onDeviceFound Invoked when a new receiver device is discovered or updated.
     * @param onDeviceLost Invoked with device ID when a receiver goes offline or times out.
     */
    fun startDiscovery(
        onDeviceFound: (CastDevice) -> Unit,
        onDeviceLost: (String) -> Unit
    )

    /**
     * Halts ongoing network discovery routines (mDNS, SSDP, or network scans).
     */
    fun stopDiscovery()

    /**
     * Instantiates an active communication session for the specified target device.
     *
     * @param device The target cast sink device.
     * @param listener Callback receiver for playback state and connection transitions.
     * @return An active {@link CastSession} managing the protocol handshake and playback controls.
     */
    fun createSession(
        device: CastDevice,
        listener: CastStateListener
    ): CastSession
}
