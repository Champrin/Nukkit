package cn.nukkit.level.generator.standard.gen.decorator;

import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.chunk.IChunk;
import cn.nukkit.level.generator.standard.pop.Populator;
import cn.nukkit.utils.Identifier;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.daporkchop.lib.random.PRandom;

/**
 * Allows individual modification of blocks in a chunk after surfaces have been built.
 * <p>
 * Similar to a populator, but only operates on an individual block column in a single chunk.
 *
 * @author DaPorkchop_
 */
@JsonDeserialize(using = DecoratorDeserializer.class)
public interface Decorator extends Populator {
    Decorator[] EMPTY_ARRAY = new Decorator[0];

    @Override
    default void populate(PRandom random, ChunkManager level, int blockX, int blockZ) {
        this.decorate(random, level.getChunk(blockX >> 4, blockZ >> 4), blockX & 0xF, blockZ & 0xF);
    }

    /**
     * Decorates a given chunk.
     *
     * @param random an instance of {@link PRandom} for generating random numbers, initialized with a seed based on chunk's position
     * @param chunk  the chunk to be decorated
     * @param x      the X coordinate of the block column in the chunk to decorate
     * @param z      the Z coordinate of the block column in the chunk to decorate
     */
    void decorate(PRandom random, IChunk chunk, int x, int z);

    @Override
    Identifier getId();
}
