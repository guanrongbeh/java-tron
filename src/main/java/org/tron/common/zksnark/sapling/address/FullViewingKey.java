package org.tron.common.zksnark.sapling.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tron.common.zksnark.sapling.Librustzcash;

// Decryption using a Full Viewing Key

@AllArgsConstructor
public class FullViewingKey {

  @Getter
  @Setter
  private byte[] ak; // 256
  @Getter
  @Setter
  private byte[] nk; // 256
  @Getter
  @Setter
  private byte[] ovk; // 256,the outgoing viewing key

  // ! Get the fingerprint of this full viewing key (as defined in ZIP 32).
  byte[] GetFingerprint() {
    return CBLAKE2bWriter.GetHash(this);
  }

  public IncomingViewingKey inViewingKey() {

    byte[] ivk = new byte[32]; // the incoming viewing key
    Librustzcash.librustzcashCrhIvk(ak, nk, ivk);
    return new IncomingViewingKey(ivk);
  }

  boolean is_valid() {
    byte[] ivk = null;
    Librustzcash.librustzcashCrhIvk(ak, nk, ivk);
    return ivk != null && ivk.length != 0;
  }

  public static class CBLAKE2bWriter {

    public static byte[] GetHash(FullViewingKey keys) {
      return null;
    }
  }

  public byte[] encode() {

    byte[] m_bytes = new byte[96];

    System.arraycopy(ak, 0, m_bytes, 0, 32);
    System.arraycopy(nk, 0, m_bytes, 32, 32);
    System.arraycopy(ovk, 0, m_bytes, 64, 32);

    return m_bytes;
  }
}
