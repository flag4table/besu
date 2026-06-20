/*
 * Copyright contributors to Besu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.besu.util.platform;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PlatformDetectorTest {

  @Test
  void glibcVersionComparisonAllowsEqualVersion() {
    assertThat(PlatformDetector.isGlibcVersionAtLeast("2.28", "2.28")).isTrue();
  }

  @Test
  void glibcVersionComparisonAllowsNewerVersion() {
    assertThat(PlatformDetector.isGlibcVersionAtLeast("2.29", "2.28")).isTrue();
  }

  @Test
  void glibcVersionComparisonRejectsOlderVersion() {
    assertThat(PlatformDetector.isGlibcVersionAtLeast("2.27", "2.28")).isFalse();
  }

  @Test
  void glibcVersionComparisonHandlesPatchVersions() {
    assertThat(PlatformDetector.isGlibcVersionAtLeast("2.28.1", "2.28")).isTrue();
    assertThat(PlatformDetector.isGlibcVersionAtLeast("2.28", "2.28.1")).isFalse();
  }

  @Test
  void glibcVersionIsKnownWhenDetected() {
    assertThat(PlatformDetector.isKnownGlibcVersion("2.28")).isTrue();
    assertThat(PlatformDetector.isKnownGlibcVersion("unknown")).isFalse();
    assertThat(PlatformDetector.isKnownGlibcVersion(null)).isFalse();
  }
}
