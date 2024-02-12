/*
 * Copyright 2013-2024, Seqera Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nextflow.cli

import com.beust.jcommander.Parameters
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import nextflow.scm.AssetManager

/**
 * CLI sub-command LIST. Prints a list of locally installed pipelines
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@Slf4j
@CompileStatic
class CmdList {

    static final public NAME = 'list'

    interface Options {}

    @Parameters(commandDescription = "List all downloaded projects")
    static class V1 extends CmdBase implements Options {

        @Override
        final String getName() { NAME }

        @Override
        void run() {
            new CmdList(this).run()
        }

    }

    @Delegate
    private Options options

    CmdList(Options options) {
        this.options = options
    }

    void run() {

        def all = AssetManager.list()
        if( !all ) {
            log.info '(none)'
            return
        }

        all.each { println it }
    }

}
