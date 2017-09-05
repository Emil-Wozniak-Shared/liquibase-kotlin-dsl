/*
 * Copyright 2011-2017 Tim Berglund and Steven C. Saliman
 * Kotlin conversion done by Jason Blackwell
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.liquibase.kotlin.helper

import liquibase.changelog.IncludeAllFilter

/**
 * This class is a helper for the {@code DatabaseChangeLogTest} class.
 * It is an implementation of the Liquibase {@code IncludeAllFilter} that
 * includes all files that have "first" in the name.
 *
 * @author Steven C. Saliman
 * @author Jason Blackwell
 */
@SuppressWarnings("unused") // It's used via reflection.
class IncludeAllFirstOnlyFilter : IncludeAllFilter {
	override fun include(changeLogPath: String): Boolean {
		return changeLogPath.contains("first")
	}
}
