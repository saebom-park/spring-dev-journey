public class MemberFactory {
    private MemberFactory() {
        // 외부에서 생성 못함!
    }

    public static FactoryMember create(String name) {
        FactoryMember m = new FactoryMember();
        m.name = name;
        return m;
    }
}