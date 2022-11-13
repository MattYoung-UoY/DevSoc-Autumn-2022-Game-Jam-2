package ghs.entities;

import java.util.ArrayList;

import dejavu.engine.graphics.Quad;
import dejavu.engine.tools.Collision;
import ghs.tiles.Tile;

public abstract class Entity {

	public boolean onGround(Quad quad, ArrayList<Tile> tiles) {
		int vertsIn = 0;

		float[][] verts = { { (-0.5f * quad.getScaleX()) + quad.getX(), (-0.5f * quad.getScaleY()) + quad.getY() },
				{ (0.5f * quad.getScaleX()) + quad.getX(), (-0.5f * quad.getScaleY()) + quad.getY() },
				{ (-0.5f * quad.getScaleX()) + quad.getX(), (0.5f * quad.getScaleY()) + quad.getY() },
				{ (0.5f * quad.getScaleX()) + quad.getX(), (0.5f * quad.getScaleY()) + quad.getY() } };

		for (float[] vert : verts) {
			for (Tile t : tiles) {
				if (Collision.pointInQuad(t.getQuad(), vert[0], vert[1])) {
					if (!t.isCollidable()) {
						vertsIn++;
						break;
					}
				}
			}
		}

		for (Tile t : tiles) {
			Quad tQuad = t.getQuad();
			float[][] tVert = {
					{ (-0.5f * tQuad.getScaleX()) + tQuad.getX(), (-0.5f * tQuad.getScaleY()) + tQuad.getY() },
					{ (0.5f * tQuad.getScaleX()) + tQuad.getX(), (-0.5f * tQuad.getScaleY()) + tQuad.getY() },
					{ (-0.5f * tQuad.getScaleX()) + tQuad.getX(), (0.5f * tQuad.getScaleY()) + tQuad.getY() },
					{ (0.5f * tQuad.getScaleX()) + tQuad.getX(), (0.5f * tQuad.getScaleY()) + tQuad.getY() } };
			for (float[] vert : tVert) {
				if (Collision.pointInQuad(quad, vert[0], vert[1])) {
					if (t.isCollidable()) {
						return false;
					}
				}
			}
		}

		return vertsIn == 4;
	}

}
