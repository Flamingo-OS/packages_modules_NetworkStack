/*
 * Copyright (C) 2019 The Android Open Source Project
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

package com.android.networkstack.apishim;

import androidx.annotation.NonNull;

import java.net.SocketAddress;

/**
 * Interface used to access API methods in {@link android.net.util.SocketUtils}, with appropriate
 * fallbacks if the methods are not yet part of the released API.
 *
 * <p>This interface makes it easier for callers to use SocketUtilsShimImpl, as it's more obvious
 * what methods must be implemented on each API level, and it abstracts from callers the need to
 * reference classes that have different implementations (which also does not work well with IDEs).
 */
public interface SocketUtilsShim {
    /**
     * Create a new instance of SocketUtilsShim.
     */
    @NonNull
    static SocketUtilsShim newInstance() {
        // TODO: when the R API is finalized, rename the API 29 shim to SocketUtilsCompat, and
        // return it here instead of SocketUtilsShimImpl for devices with Build.VERSION <= 29.
        // For now, the switch between implementations is done at build time (swapping the java file
        // with another), since production modules should not be built with a non-finalized API.
        return new SocketUtilsShimImpl();
    }

    /**
     * @see android.net.util.SocketUtils#makePacketSocketAddress(int, int, byte[])
     */
    @NonNull
    SocketAddress makePacketSocketAddress(int protocol, int ifIndex, @NonNull byte[] hwAddr);
}
