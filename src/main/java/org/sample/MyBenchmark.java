/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.*;

import imageProcessing.GrayLevelProcessing;

import net.imglib2.img.Img;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import java.io.File;


import io.scif.img.ImgIOException;
import net.imglib2.exception.IncompatibleTypeException;

public class MyBenchmark {
    
    // On init les var uniquement pour chaque thread de notre benchmark (ici 1 normalement)
    @State(Scope.Benchmark)
    public static class ThreadState {

        /* 
        * Le setup est exec avant le bench 
        * Level.Trial nous assure que la méthode est executé une seule fois pour tout les run 
        */
        @Setup(Level.Trial)
        public void doSetup() {
            final String filename = "C:\\Users\\alexi\\Documents\\L3Info\\Projet\\TP1\\pdl-tp1\\ressources\\images\\hamac.jpg";
            final Img<UnsignedByteType> input = GrayLevelProcessing.createImg(filename);
        }

        // Cette méthode est exec après le benchmark pour faire le ménage (s'en servir pour écrire l'image dans l'output ???)
        @TearDown(Level.Trial)
        public void clean() {
            System.out.println("exec à la fin\n");
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 0)
    public void Test_addLumen(ThreadState state) {
        //GrayLevelProcessing.addLumen(state.input, 50);
    }
}
