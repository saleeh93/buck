/*
 * Copyright 2014-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.android;

import com.facebook.buck.java.JavaCompilerEnvironment;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.rules.AbstractBuilder;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.PathSourcePath;
import com.facebook.buck.rules.SourcePath;
import com.google.common.base.Optional;

import java.nio.file.Path;

public class AndroidLibraryBuilder extends AbstractBuilder<AndroidLibraryDescription.Arg> {

  private AndroidLibraryBuilder(BuildTarget target) {
    super(new AndroidLibraryDescription(JavaCompilerEnvironment.DEFAULT), target);
  }

  public static AndroidLibraryBuilder createBuilder(BuildTarget target) {
    return new AndroidLibraryBuilder(target);
  }

  public AndroidLibraryBuilder addProcessor(String processor) {
    arg.annotationProcessors = amendSet(arg.annotationProcessors, processor);
    return this;
  }

  public AndroidLibraryBuilder addProcessorBuildTarget(BuildRule processorRule) {
    arg.annotationProcessorDeps = amend(arg.annotationProcessorDeps, processorRule);
    return this;
  }

  public AndroidLibraryBuilder setManifestFile(SourcePath manifestFile) {
    arg.manifest = Optional.of(manifestFile);
    return this;
  }

  public AndroidLibraryBuilder addDep(BuildRule rule) {
    arg.deps = amend(arg.deps, rule);
    return this;
  }

  public AndroidLibraryBuilder addExportedDep(BuildRule rule) {
    arg.exportedDeps = amend(arg.exportedDeps, rule);
    return this;
  }

  public AndroidLibraryBuilder addSrc(Path path) {
    arg.srcs = amend(arg.srcs, new PathSourcePath(path));
    return this;
  }
}
