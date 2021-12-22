public class test {
    /**
     * This sketch demonstrates two ways to accomplish offline (non-realtime) analysis of an audio file.<br>
     * The first method, which uses an AudioSample, is what you see running.<br>
     * The second method, which uses an AudioRecordingStream and is only available in Minim Beta 2.1.0 and beyond,<br>
     * can be viewed by looking at the offlineAnalysis.pde file.
     * <p>
     * For more information about Minim and additional features, visit http://code.compartmental.net/minim/
     *
     */

import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.spi.*;

    Minim minim;
    float[][] spectra;

    void setup()
    {
        size(1000, 1000, P3D);

        minim = new Minim(this);

        // There are two ways you can do offline analysis:
        // 1. Loading audio data fully into memory using an AudioSample and then analyzing a channel
        analyzeUsingAudioSample();

        // 2. Loading an AudioRecordingStream and reading in a buffer at a time.
        //    This second option is available starting with Minim Beta 2.1.0
        //analyzeUsingAudioRecordingStream();
    }

    void analyzeUsingAudioSample()
    {
        //AudioSample jingle = minim.loadSample("bump.mp3", 2048);
        AudioSample jingle = minim.loadSample("keys riffs.ogg.mp3", 2048);

        // get the left channel of the audio as a float array
        // getChannel is defined in the interface BuffereAudio,
        // which also defines two constants to use as an argument
        // BufferedAudio.LEFT and BufferedAudio.RIGHT
        float[] leftChannel = jingle.getChannel(AudioSample.LEFT);

        // then we create an array we'll copy sample data into for the FFT object
        // this should be as large as you want your FFT to be. generally speaking, 1024 is probably fine.
        int fftSize = 1024;
        float[] fftSamples = new float[fftSize];
        FFT fft = new FFT( fftSize, jingle.sampleRate() );

        // now we'll analyze the samples in chunks
        int totalChunks = (leftChannel.length / fftSize) + 1;

        // allocate a 2-dimentional array that will hold all of the spectrum data for all of the chunks.
        // the second dimension if fftSize/2 because the spectrum size is always half the number of samples analyzed.
        spectra = new float[totalChunks][fftSize/2];

        for(int chunkIdx = 0; chunkIdx < totalChunks; ++chunkIdx)
        {
            int chunkStartIndex = chunkIdx * fftSize;

            // the chunk size will always be fftSize, except for the
            // last chunk, which will be however many samples are left in source
            int chunkSize = min( leftChannel.length - chunkStartIndex, fftSize );

            // copy first chunk into our analysis array
            arraycopy( leftChannel, // source of the copy
                    chunkStartIndex, // index to start in the source
                    fftSamples, // destination of the copy
                    0, // index to copy to
                    chunkSize // how many samples to copy
            );

            // if the chunk was smaller than the fftSize, we need to pad the analysis buffer with zeroes
            if ( chunkSize < fftSize )
            {
                // we use a system call for this
                Arrays.fill( fftSamples, chunkSize, fftSamples.length - 1, 0.0 );
            }

            // now analyze this buffer
            fft.forward( fftSamples );

            // and copy the resulting spectrum into our spectra array
            for(int i = 0; i < 512; ++i)
            {
                spectra[chunkIdx][i] = fft.getBand(i);
            }
        }

        jingle.close();
    }

    void draw()
    {
        // jump back to start position when we get to the end

        background(0);
        stroke(255);

        // render the spectra going back into the screen
        float scaleMod = (float(width) / float(spectra.length));
        println("--");
        println(width);
        println(spectra.length);
        println(scaleMod);

        for(int s = 0; s < spectra.length; s++)
        {
            stroke(255);
            int i =0;
            float total = 0;
            for(i = 0; i < spectra[s].length-1; i++)
            {
                total += spectra[s][i];
            }
            total = total / 10;
            line(s*scaleMod,total+height/2,s*scaleMod,-total+height/2);
        }
        stroke(255,0,0);
        line(spectra.length * scaleMod, 1000, spectra.length *scaleMod, 0+300);
    }
}
