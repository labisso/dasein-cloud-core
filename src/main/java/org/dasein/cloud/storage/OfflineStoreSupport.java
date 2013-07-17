/*
 * Copyright (C) 2009-2013 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.storage;

import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.util.uom.storage.*;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.concurrent.Future;

/**
 * Extends BlobStoreSupport with async methods for potentially long-running jobs
 *
 * Requests may be coalesced with existing jobs that are identical. For example if
 * a request to list a bucket contents is pending and another is made, the driver
 * may choose to not create a new request and instead use the existing one.
 *
 * Async operations return a Future object, which can get polled or queried by the
 * caller as desired. When a result is ready, the Future will be populated.
 *
 * The caller must beware when using the synchronous functions in the parent
 * interface as they may block for a very long time.
 */
public interface OfflineStoreSupport extends BlobStoreSupport {

    public Future<FileTransfer> downloadAsync(@Nullable String bucket, @Nonnull String objectName, @Nonnull File toFile) throws InternalException, CloudException;

    public Future<Blob> getObjectAsync(@Nullable String bucketName, @Nonnull String objectName) throws InternalException, CloudException;

    public @Nullable Future<Storage<org.dasein.util.uom.storage.Byte>> getObjectSizeAsync(@Nullable String bucketName, @Nullable String objectName) throws InternalException, CloudException;

    public @Nonnull Future<Iterable<Blob>> listAsync(@Nullable String bucket) throws CloudException, InternalException;

}
