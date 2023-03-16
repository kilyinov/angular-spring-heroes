package heroes.nativehints;

import java.lang.reflect.Constructor;

import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

public class LiquibaseNativeHints implements RuntimeHintsRegistrar {

  @Override
  public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
     registerConstructor(hints, liquibase.sql.visitor.AppendSqlVisitor.class);
     registerConstructor(hints, liquibase.sql.visitor.PrependSqlVisitor.class);
     registerConstructor(hints, liquibase.sql.visitor.RegExpReplaceSqlVisitor.class);
     registerConstructor(hints, liquibase.sql.visitor.ReplaceSqlVisitor.class);

     registerPublicMethods(hints, liquibase.change.core.AlterSequenceChange.class);
     registerPublicMethods(hints, liquibase.change.core.CreateIndexChange.class);
     registerPublicMethods(hints, liquibase.change.core.CreateProcedureChange.class);
     registerPublicMethods(hints, liquibase.change.core.CreateSequenceChange.class);
     registerPublicMethods(hints, liquibase.change.core.CreateViewChange.class);
     registerPublicMethods(hints, liquibase.change.core.DeleteDataChange.class);

    hints.resources().registerPattern("db/changelog/*.xml");
  }

  private static <T> void registerConstructor(RuntimeHints hints, Class<T> clazz) {
    try {
      Constructor<T> constructor = ReflectionUtils.accessibleConstructor(clazz);
      hints.reflection().registerConstructor(constructor, ExecutableMode.INVOKE);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  private static <T> void registerPublicMethods(RuntimeHints hints, Class<T> clazz) {
    hints.reflection().registerType(clazz, MemberCategory.INVOKE_PUBLIC_METHODS);
  }
}
