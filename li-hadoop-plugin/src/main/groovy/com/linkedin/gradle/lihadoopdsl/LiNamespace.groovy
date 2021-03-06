/*
 * Copyright 2015 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.linkedin.gradle.lihadoopdsl;

import com.linkedin.gradle.hadoopdsl.HadoopDslMethod;
import com.linkedin.gradle.hadoopdsl.NamedScope;
import com.linkedin.gradle.hadoopdsl.Namespace;
import com.linkedin.gradle.lihadoopdsl.lijob.LiPigBangBangJob;
import com.linkedin.gradle.lihadoopdsl.lijob.PigLiJob;
import org.gradle.api.Project;

/**
 * LinkedIn-specific customizations to Namespace that allow for additional LinkedIn-specific job
 * types to be created in the namespace.
 */
class LiNamespace extends Namespace implements LiNamedScopeContainer {
  /**
   * Base constructor for a LiNamespace.
   *
   * @param name The namespace name
   * @param project The Gradle project
   */
  LiNamespace(String name, Project project) {
    super(name, project);
  }

  /**
   * Constructor for a LiNamespace given a parent scope.
   *
   * @param name The namespace name
   * @param project The Gradle project
   * @param parentScope The parent scope
   */
  LiNamespace(String name, Project project, NamedScope parentScope) {
    super(name, project, parentScope);
  }

  /**
   * Clones the namespace.
   *
   * @return The cloned namespace
   */
  LiNamespace clone() {
    return clone(new LiNamespace(name, project, null));
  }

  /**
   * Helper method to set the properties on a cloned namespace.
   *
   * @param namespace The namespace being cloned
   * @return The cloned namespace
   */
  protected LiNamespace clone(LiNamespace namespace) {
    return ((LiNamespace)super.clone(namespace));
  }

  /**
   * DSL LiPigBangBangJob method creates a LiPigBangBangJob in namespace scope with the given name
   * and configuration.
   *
   * @param name The job name
   * @param configure The configuration closure
   * @return The new job
   */
  @HadoopDslMethod
  LiPigBangBangJob liPigBangBangJob(String name, @DelegatesTo(LiPigBangBangJob) Closure configure) {
    return ((LiPigBangBangJob)configureJob(((LiHadoopDslFactory)factory).makeLiPigBangBangJob(name), configure));
  }

  /**
   * @deprecated PigLiJob now has no differences with PigJob.
   *
   * DSL pigLiJob method. Creates a PigLiJob in namespace scope with the given name and
   * configuration.
   *
   * @param name The job name
   * @param configure The configuration closure
   * @return The new job
   */
  @Deprecated
  @HadoopDslMethod
  PigLiJob pigLiJob(String name, @DelegatesTo(PigLiJob) Closure configure) {
    return ((PigLiJob)configureJob(((LiHadoopDslFactory)factory).makePigLiJob(name), configure));
  }
}
