package flow.twist.mains;

import java.util.Set;

import flow.twist.AbstractMainAnalysis;
import flow.twist.SolverFactory;
import flow.twist.config.AnalysisConfigurationBuilder;
import flow.twist.path.Path;
import flow.twist.reporter.ConsoleReporter;
import flow.twist.transformer.StoreDataTransformer;
import flow.twist.transformer.path.MergeEqualSelectorStrategy;
import flow.twist.transformer.path.PathBuilderResultTransformer;

public class BiDiRA {
  public BiDiRA(int numCores, int N, String[] args) {
		new AbstractMainAnalysis(args) {
			@Override
			protected Set<Path> _executeAnalysis() {
        return null;
      }
			@Override
			protected void executeAnalysis() {
				SolverFactory.runBiDiRASolver(AnalysisConfigurationBuilder.i2oSimpleClassForNameDefaults().reporter(
						new ConsoleReporter()), numCores);
			}
		}.execute("ra", "soot", "bidi", numCores, N);
	}
}
